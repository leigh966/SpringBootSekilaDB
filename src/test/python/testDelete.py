import requests
import tools

def test_delete_film(root):
    id = requests.get(root+"get_last_film_id").text
    pload = {"id":int(id)}
    r = requests.delete(root+"delete_film", params=pload)
    if not r.ok:
        print(r.text)
        return "test failed: error"
    if(r.text == "deleted"):
        return "test passed: film successfully deleted"
    return tools.create_fail_message("film not deleted", "deleted", r.text)