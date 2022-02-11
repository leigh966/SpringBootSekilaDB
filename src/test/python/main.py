import requests;
import json;

def add_film():


def test_films_all_upper():
    r = requests.get("http://localhost:8080/home/get_film")
    films = r.json()
    for film in films:
        #print(film["title"])
        if film["title"] != film["title"].upper():
            return "test failed: title=" + film["title"] + ", expected_title="+film["title"].upper()
    return "test passed"

print(test_films_all_upper())

