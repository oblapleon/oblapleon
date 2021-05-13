from appium import webdriver
from selenium.webdriver.support.ui import WebDriverWait
from hamcrest import assert_that
import time

class TestLogin:

    def test_login(self):
        # STEP 1. ЗАДАЁМ DESIRED CAPS

        desired_caps = {
            "appPackage": "com.facebook.katana",
            "appActivity": "com.facebook.katana.LoginActivity",
            "platformName": "Android",
            "platformVersion": "11.0"
        }

        sleep = 5
        # STEP 1.5. УКАЗЫВАЕМ СЕССИЮ ДРАЙВЕРА, ПЕРЕДАЕМ DESIRED CAPS в СЕССИЮ.
        driver = webdriver.Remote("http://localhost:4723/wd/hub", desired_caps)
        time.sleep(sleep)
        # STEP 2. ЗАДАЁМ XPATH
        username_xpath = '//android.widget.EditText[@content-desc="Username"]'
        password_xpath = '//android.widget.EditText[@content-desc="Password"]'
        login_button_assesability_id = 'Log In'
        error_pop_up_resource_id = 'android:id/content'

        # STEP 3. СОЗДАЁМ ПЕРЕМЕННЫЕ ПОД АЙДИШНИКИ ИЗ АППИУМ ИНСПЕКТОРА.
        USERNAME_FIELD = driver.find_element_by_xpath(username_xpath)
        PASSWORD_FIELD = driver.find_element_by_xpath(password_xpath)
        LOGIN_BUTTON = driver.find_element_by_accessibility_id(login_button_assesability_id)
        ERROR_POP_UP = driver.find_element_by_id(error_pop_up_resource_id)

        # INPUT FOR TEST
        login = 'ABABABABA@gmail.com'
        password = '1234567'

        print('INPUT LOGIN')
        USERNAME_FIELD.send_keys(login)
        time.sleep(sleep)
        print('INPUT PASSWORD')
        PASSWORD_FIELD.send_keys(password)
        time.sleep(sleep)
        PASSWORD_FIELD.click()
        time.sleep(sleep)
        print('CLICK LOGIN BUTTON')
        LOGIN_BUTTON.click()
        time.sleep(sleep)
        assert_that(ERROR_POP_UP.is_displayed(),  'ERROR POP-UP SHOULD BE DISPLAYED.')
