import re
import requests;
import json;

root = "http://localhost:8080/home/"



def create_fail_message(info, expected, actual):
    return "test failed: " + info + " - " + "actual=" + actual + ", expected=" + expected

def add_film_min():
    pload = {
    "title":"a film",
    "description":"it's a film",
    "language_id":'1',
    "rental_duration":'5',
    "rental_rate":'2.99',
    "replacement_cost":'10.5',
    "rating":"R"
      }
    r = requests.post(root+"add_film",params=pload)
    return r.text


add_film_min_test_result = add_film_min()
if add_film_min_test_result == "saved":
    print("test passed: successfully saved new film")
else:
    print("test failed: failed to save new film - " + add_film_min_test_result)


# All film titles should be uppercase
def test_films_all_upper():
    r = requests.get(root+"get_film")
    if not r.ok:
        print(r.text)
        return "test failed: bad request"
    films = r.json()
    for film in films:
        #print(film["title"])
        if film["title"] != film["title"].upper():
            return "test failed: title should be all uppercase - title=" + film["title"] + ", expected_title="+film["title"].upper()
    return "test passed: All titles are uppercase"
print(test_films_all_upper())

def test_actors_all_upper():
    r = requests.get(root+"get_actor")
    if not r.ok:
        print(r.text)
        return "test failed: bad request"
    #print(r.text)
    actors = r.json()
    for actor in actors:
        #print(actor)
        first_name = actor["first_name"]
        if first_name != first_name.upper():
            return create_fail_message("first_name should be all uppercase", first_name.upper(), first_name)
        last_name = actor["last_name"]
        if last_name != last_name.upper():
            return create_fail_message("last_name should be all uppercase", last_name.upper(), last_name)
    return "test passed: All actor names are uppercase"
print(test_actors_all_upper())
"""
def test_delete_actor():
    r = requests.delete(root+"delete_actor")
    if not r.ok:
        print(r.text)
        return "test failed: bad request"
"""

def test_delete_film():
    id = requests.get(root+"get_last_film_id").text
    pload = {"id":int(id)}
    r = requests.delete(root+"delete_film", params=pload)
    if not r.ok:
        print(r.text)
        return "test failed: bad request"
    if(r.text == "deleted"):
        return "test passed: film successfully deleted"
    return create_fail_message("film not deleted", "deleted", r.text)

print(test_delete_film())

    