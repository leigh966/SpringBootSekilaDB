import requests
import tools

def add_film_min(root,
    title="a film",
 description="it's a film",
  language_id=1,
  rental_duration=5,
  rental_rate=2.99,
  replacement_cost=10.5,
  rating='R'
  ):
    pload = {
    "title":title,
    "description":description,
    "language_id":str(language_id),
    "rental_duration":str(rental_duration),
    "rental_rate":str(rental_rate),
    "replacement_cost":str(10.5),
    "rating":rating
      }
    r = requests.post(root+"add_film",params=pload)
    add_film_min_test_result = r.text
    if add_film_min_test_result == "saved":
        return "test passed: successfully saved new film"
    return "test failed: failed to save new film - " + add_film_min_test_result
