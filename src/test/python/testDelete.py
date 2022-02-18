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

def unlink_film_actor_link(root, actor_id, film_id):
    pload = {"actor_id":actor_id, "film_id":film_id}
    r = requests.delete(root+"link_actor_film", params=pload)
    return r.text


def test_unlink_added_film_actor_link(root):
    text = unlink_film_actor_link(root, 2, 1)
    if text == "unlinked":
        return "test passed: backend reports that the link has been removed"
    return tools.create_fail_message("unlinking actor_id(2) from film_id(1) should have worked", "return_text=unlinked", "return_text="+text)
