# Idealwine API (retrieve wine quotations)

API to retrieve quotes from daily wine auctions

## Get Started

```
git clone git@github.com:philipperemy/Idealwine-API.git
cd Idealwine-API/python

# Virtual environment: Recommended
virtualenv -p python3.6 venv
source venv/bin/activate
pip install -r requirements.txt

# Install ChromeDriver here:
# http://chromedriver.chromium.org/downloads

# Make sure Selenium and all the python deps are properly installed (including ChromeDriver) before running the main script.
export IDEALWINE_USER=<MY_USER>; export IDEALWINE_PASS=<MY_PASS>; python main.py
```

All the screenshots will be available in the folder `screenshots`.

## Example of output

<p align="center">
  <img src="assets/1983_Bordeaux-Saint-Emilion-Chateau-Pavie-1er-Grand-Cru-Classe-A-Rouge_527_out.png" width="500">
  <br><i>Example: Quotations of Chateau Pavie 1er Grand Cru Classee 1983 indexed by time. Y-axis unit is EUR.</i>
</p>
