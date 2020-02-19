import requests


# Prerequest -- get token for username=admin, password=qwerty
def get_token():
    url = "http://mysite.com/api/auth/"
    data = {"username": "admin",
            "password": "qwerty"}
    headers = {'Content-type': 'application/json'}
    response = requests.post(url, json=data, headers=headers)
    response_body = response.json()
    return response_body["token"]


token = "token " + get_token()


# TC4. Check report submitted priority value 1 [positive]
def test_check_report_submitted_priority_value_1():
    url = "http://mysite.com/api/submit_report/"
    data = {"priority": 1,
            "report": "sometext"}
    headers = {'Content-type': 'application/json', 
               "Authorization": token}
    response = requests.post(url, json=data, headers=headers)
    assert response.status_code == 200


# TC5. Check report submitted priority value 5 [positive]
def test_check_report_submitted_priority_value_5():
    url = "http://mysite.com/api/submit_report/"
    data = {"priority": 5,
            "report": "sometext"}
    headers = {'Content-type': 'application/json', 
               "Authorization": token}
    response = requests.post(url, json=data, headers=headers)
    assert response.status_code == 200


# TC6. Check report not submitted priority value 0 [negative]
def test_check_report_not_submitted_priority_value_0():
    url = "http://mysite.com/api/submit_report/"
    data = {"priority": 0,
            "report": "sometext"}
    headers = {'Content-type': 'application/json',
               "Authorization": "token " + token}
    response = requests.post(url, json=data, headers=headers)
    assert response.status_code == 400


# TC7. Check report not submitted priority value 6 [negative]
def test_check_report_not_submitted_priority_value_6():
    url = "http://mysite.com/api/submit_report/"
    data = {"priority": 6,
            "report": "sometext"}
    headers = {'Content-type': 'application/json',
               "Authorization": "token " + token}
    response = requests.post(url, json=data, headers=headers)
    assert response.status_code == 400


# TC8. Check report not submitted report value not a string [negative]
def test_check_report_not_submitted_report_not_a_string():
    url = "http://mysite.com/api/submit_report/"
    data = {"priority": 5,
            "report": 1111}
    headers = {'Content-type': 'application/json',
               "Authorization": "token " + token}
    response = requests.post(url, json=data, headers=headers)
    assert response.status_code == 400


# TC9. Check report not submitted empty token [negative]
def test_check_report_not_submitted_token_empty():
    url = "http://mysite.com/api/submit_report/"
    data = {"priority": 1,
            "report": "somereport"}
    headers = {'Content-type': 'application/json',
               "Authorization": ""}
    response = requests.post(url, json=data, headers=headers)
    assert response.status_code == 403


# TC10. Check report not submitted empty body. [negative]
def test_check_report_not_submitted_empty_request_body():
    url = "http://mysite.com/api/submit_report/"
    data = {}
    headers = {'Content-type': 'application/json',
               "Authorization": "token " + token}
    response = requests.post(url, json=data, headers=headers)
    assert response.status_code == 400
