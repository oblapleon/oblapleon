import requests


# TC1. Check token received for permitted user. [positive]
def test_check_token_received_for_permitted_user():
    url = "http://mysite.com/api/auth/"
    data = {"username": "admin",
            "password": "qwerty"}
    headers = {'Content-type': 'application/json'}
    response = requests.post(url, json=data, headers=headers)
    assert response.status_code == 200
    assert response.headers['Content-Type'] == "application/json"
    response_body = response.json()
    assert response_body["token"] == "here come some token"


# TC2. Check token not received for not permitted user. [negative]
def test_check_token_not_received_for_not_permitted_user():
    url = "http://mysite.com/api/auth/"
    data = {"username": "some_user",
            "password": "some_password"}
    headers = {'Content-type': 'application/json'}
    response = requests.post(url, json=data, headers=headers)
    assert response.status_code == 400


# TC3. Check token not received in case with empty body. [negative]
def test_check_token_not_received_empty_body():
    url = "http://mysite.com/api/auth/"
    data = {}
    headers = {'Content-type': 'application/json'}
    response = requests.post(url, json=data, headers=headers)
    assert response.status_code == 400
