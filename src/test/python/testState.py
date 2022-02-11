import requests
import tools

# All film titles should be uppercase
def test_films_all_upper(root):
    r = requests.get(root+"get_film")
    if not r.ok:
        print(r.text)
        return "test failed: error"
    films = r.json()
    for film in films:
        if film["title"] != film["title"].upper():
            return "test failed: title should be all uppercase - title=" + film["title"] + ", expected_title="+film["title"].upper()
    return "test passed: All titles are uppercase"

def test_actors_all_upper(root):
    r = requests.get(root+"get_actor")
    if not r.ok:
        print(r.text)
        return "test failed: error"
    actors = r.json()
    for actor in actors:
        first_name = actor["first_name"]
        if first_name != first_name.upper():
            return tools.create_fail_message("first_name should be all uppercase", first_name.upper(), first_name)
        last_name = actor["last_name"]
        if last_name != last_name.upper():
            return tools.create_fail_message("last_name should be all uppercase", last_name.upper(), last_name)
    return "test passed: All actor names are uppercase"