import time

import json
import os
from bs4 import BeautifulSoup
from selenium import webdriver
from selenium.common.exceptions import TimeoutException


def forge_url(wine_id, wine_name, millesime):
    url = 'https://www.idealwine.com/fr/prix-vin/{0}-{1}-Bouteille-{2}.jsp'.format(wine_id, millesime, wine_name)
    return url


MILLESIMES = range(1982, 2018)
WINES = json.load(open('wines.json', 'r'))

count = 0


def get_fields(wine_id, wine_name, millesime, driver):
    global count
    url = forge_url(wine_id, wine_name, millesime)
    driver.get(url)
    soup = BeautifulSoup(driver.page_source, 'html.parser')
    wine_price = str([v for v in soup.find_all('a')
                      if 'Cliquer pour voir la cote' in str(v)][0].contents[0].contents[0]).strip()

    # parker_rating = ''
    # try:
    #     if '(' in parker_rating:
    #         if '+' in parker_rating:
    #             # (92+)
    #             parker_rating = str(int(parker_rating[1:-2]))
    #         else:
    #             # (89-92)
    #             parker_rating = str(int(sum([int(e) for e in parker_rating[1:-1].split('-')]) / 2))
    # except:
    #     pass
    time.sleep(1)

    bb2 = bytes(
        driver.execute_script("return $('canvas')[0].toDataURL('image/png');").replace('data:image/png;base64,', ''),
        encoding='utf-8')
    import base64

    photo_id = '{}_{}_{}'.format(millesime, wine_name, wine_id)
    input_filename = 'screenshots/{}_{}.png'.format(count, photo_id)
    count += 1

    with open(input_filename, 'wb') as fh:
        fh.write(base64.decodebytes(bb2))

    from PIL import Image
    from PIL import ImageFont
    from PIL import ImageDraw
    img = Image.open(input_filename)
    draw = ImageDraw.Draw(img)
    font = ImageFont.truetype('Arial Unicode.ttf', 25)
    draw.text((50, 50), '{}_{}'.format(millesime, wine_name), (255, 0, 0), font=font)
    output_filename = input_filename.split('.')[0] + '_out.' + input_filename.split('.')[1]
    img.save(output_filename)
    print('-> wrote to {}'.format(output_filename))
    os.remove(input_filename)

    # time.sleep(1)
    # driver.execute_script("window.history.back();")
    # time.sleep(1)
    return wine_price


def main():
    driver = webdriver.Chrome('/usr/local/bin/chromedriver')
    driver.get('https://www.idealwine.com/fr/my_idealwine/login.jsp?dest=fr/my_idealwine/accueil_profil.jsp')
    time.sleep(1)
    driver.find_element_by_id('ident').send_keys(os.environ['IDEALWINE_USER'])
    time.sleep(1)
    driver.find_element_by_id('pswd').send_keys(os.environ['IDEALWINE_PASS'])
    time.sleep(1)
    driver.find_element_by_name('ok').click()
    time.sleep(1)

    driver.get('https://www.idealwine.com/fr/cote.jsp')

    driver.find_element_by_id('s').send_keys('Chateau Haut Marbuzet')
    driver.find_element_by_id('searchbtn2').click()

    write_mode = 'w' if count == 0 else 'a'
    with open('output.csv', write_mode) as w:
        w.write('\ntechnical_name, millesime, price\n')
        for (technical_name, wine_id, wine_name) in WINES:
            for millesime in MILLESIMES:
                while True:
                    try:
                        price = get_fields(wine_id, wine_name, millesime, driver)
                        line = ', '.join([technical_name, str(millesime), price])
                        w.write(line + '\n')
                        w.flush()
                        break
                    except IndexError:
                        print('Millesime {0} does not exist for wine {1}.'.format(millesime, technical_name))
                        break
                    except TimeoutException:
                        print('TimeOut exception occurred. Resuming.')
                        time.sleep(10)
                        # looping only here.

    driver.quit()


if __name__ == '__main__':
    main()
