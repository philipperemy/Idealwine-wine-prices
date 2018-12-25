import time

import base64
import json
import logging
import os
import random
from PIL import Image
from PIL import ImageDraw
from PIL import ImageFont
from bs4 import BeautifulSoup
from selenium import webdriver
from selenium.common.exceptions import TimeoutException

MILLESIMES = list(range(1982, 2016))
WINES = json.load(open('wines.json', 'r'))
logger = logging.getLogger(__name__)


def forge_url(wine_id, wine_name, millesime):
    return 'https://www.idealwine.com/fr/prix-vin/{0}-{1}-Bouteille-{2}.jsp'.format(wine_id, millesime, wine_name)


def get_filename(millesime, wine_name, wine_id):
    photo_id = '{}_{}_{}'.format(millesime, wine_name, wine_id)
    return 'screenshots/{}.png'.format(photo_id)


def process_and_return_price(wine_id, wine_name, millesime, driver, waiting_time_multiplier=1):
    url = forge_url(wine_id, wine_name, millesime)
    logger.info(url)
    driver.get(url)
    soup = BeautifulSoup(driver.page_source, 'html.parser')
    wine_price = str([v for v in soup.find_all('a')
                      if 'Cliquer pour voir la cote' in str(v)][0].contents[0].contents[0]).strip()
    # time.sleep(1 * waiting_time_multiplier)

    driver.execute_script("window.scrollTo(0, 0)")
    max_len = driver.execute_script('return document.body.offsetHeight') + 1000
    for i in list(range(0, max_len, 100)):
        time.sleep(0.04 * waiting_time_multiplier)
        driver.execute_script("window.scrollTo(0, {})".format(i))

    # driver.execute_script("window.scrollTo(0, 100000)")
    for i in list(reversed(range(0, max_len, 100))):
        time.sleep(0.04 * waiting_time_multiplier)
        driver.execute_script("window.scrollTo(0, {})".format(i))

    # time.sleep(1 * waiting_time_multiplier)
    canvas = driver.execute_script("return $('#canvas-courbe-cote')[0].toDataURL('image/png');")
    # canvas = driver.execute_script("return $('canvas')[0].toDataURL('image/png');")
    canvas = canvas.replace('data:image/png;base64,', '')
    bb2 = bytes(canvas, encoding='utf-8')

    if not os.path.exists('screenshots'):
        os.makedirs('screenshots')

    input_filename = get_filename(millesime, wine_name, wine_id)
    with open(input_filename, 'wb') as fh:
        fh.write(base64.decodebytes(bb2))

    img = Image.open(input_filename)
    draw = ImageDraw.Draw(img)
    font = ImageFont.truetype('Arial Unicode.ttf', 25)
    draw.text((50, 50), '{}_{}'.format(millesime, wine_name), (255, 0, 0), font=font)
    output_filename = input_filename.split('.')[0] + '_out.' + input_filename.split('.')[1]
    img.save(output_filename)
    logger.info('-> wrote to {}.'.format(output_filename))
    os.remove(input_filename)

    if os.path.getsize(output_filename) < 20000:  # 20k is blank image.
        os.remove(output_filename)
        raise FileNotFoundError()

    # time.sleep(1)
    # driver.execute_script("window.history.back();")
    # time.sleep(1)
    return wine_price


def get_wine_processing_list():
    total_possible_count = 0
    wine_processing_list = []
    for millesime in MILLESIMES:
        for (technical_name, wine_id, wine_name) in WINES:
            total_possible_count += 1
            output_filename = get_filename(millesime, wine_name, wine_id)[:-4] + '_out.png'
            if not os.path.isfile(output_filename):
                wine_processing_list.append((millesime, technical_name, wine_id, wine_name))
    random.shuffle(wine_processing_list)
    return wine_processing_list, total_possible_count


def main():
    print('Initialization of Chrome...')
    options = webdriver.ChromeOptions()
    options.add_argument('headless')
    driver = webdriver.Chrome(executable_path='chromedriver', options=options)
    driver.get('https://www.idealwine.com/fr/my_idealwine/login.jsp?dest=fr/my_idealwine/accueil_profil.jsp')
    driver.implicitly_wait(10)
    print('Initialization done...')
    driver.find_element_by_id('ident').send_keys(os.environ['IDEALWINE_USER'])
    driver.find_element_by_id('pswd').send_keys(os.environ['IDEALWINE_PASS'])
    driver.find_element_by_name('ok').click()

    print('Scraping has started...')
    driver.get('https://www.idealwine.com/fr/cote.jsp')

    driver.find_element_by_id('s').send_keys('Chateau Haut Marbuzet')
    driver.find_element_by_id('searchbtn2').click()

    wine_processing_list, total_possible_count = get_wine_processing_list()
    with open('output.csv', 'a') as fp:
        while len(wine_processing_list) > 0:

            # wine_processing_list, total_possible_count = get_wine_processing_list()
            progress_pct = (total_possible_count - len(wine_processing_list)) / total_possible_count * 100
            logger.info('{0} wines left to query [{1:.3f} % done].'.format(len(wine_processing_list), progress_pct))
            (millesime, technical_name, wine_id, wine_name) = wine_processing_list[0]
            waiting_time_multiplier = 1
            while True:
                output_filename = get_filename(millesime, wine_name, wine_id)[:-4] + '_out.png'
                if not os.path.isfile(output_filename):
                    try:
                        price = process_and_return_price(wine_id, wine_name, millesime,
                                                         driver, waiting_time_multiplier)
                        line = ', '.join([technical_name, str(millesime), price])
                        fp.write(line + '\n')
                        fp.flush()
                        wine_processing_list.remove((millesime, technical_name, wine_id, wine_name))
                        break
                    except IndexError:
                        logger.info('Millesime {0} does not exist for wine {1}.'.format(millesime, technical_name))
                        wine_processing_list.remove((millesime, technical_name, wine_id, wine_name))
                        break
                    except TimeoutException:
                        logger.info('TimeOut exception occurred. Resuming.')
                    except FileNotFoundError:
                        logger.warning('Blank page detected. Retrying after 5 seconds. '
                                       'Waiting time multiplier {}.'.format(waiting_time_multiplier))
                        waiting_time_multiplier *= 2

                        if waiting_time_multiplier == 16:
                            logger.warning('Critical.')

                        if waiting_time_multiplier >= 32:
                            logger.error('Could not get it after a while. Giving up.')
                            wine_processing_list.remove((millesime, technical_name, wine_id, wine_name))
                            break

                        time.sleep(1)
                else:
                    logger.info('Already there: {}.'.format(output_filename))
                    wine_processing_list.remove((millesime, technical_name, wine_id, wine_name))
                    break

    driver.quit()


if __name__ == '__main__':
    logging.basicConfig(format='%(asctime)s - %(levelname)s - %(message)s', level=logging.INFO)
    main()
