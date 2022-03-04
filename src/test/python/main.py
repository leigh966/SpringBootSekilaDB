
import testGet
import testPost
import testDelete
import testState
import testPut
import sys
root = "http://localhost:8080/home/"



if len(sys.argv) > 1:
    root = "http://" + sys.argv[1] + "/home/"

print("Root: " + root)

test_results = [
    # Update an actor
    testPut.test_put_actor_name(root),
    # Add a film with minimum required params
    testPost.add_film_min(root),
    # Ensure all film titles are caps
    testState.test_films_all_upper(root),
    # Ensure all actor names are caps
    testState.test_actors_all_upper(root),
    # Delete film
    testDelete.test_delete_film(root),
    # Get an actor by id
    testGet.test_get_actor_by_id(root),
    # Get an film by id
    testGet.test_get_film_by_id(root),
    # Query actor table by name
    testGet.test_get_actor_upper(root), # Upper-case
    testGet.test_get_actor_lower(root), # Lower-case
    # Try to get get a non-existant actor by id - making sure it gives an empty list rather than an error
    testGet.test_get_null_actor(root),
    # Try to get a film by the actor_id of an actor that is in it
    testGet.test_get_films_by_actor_id(root)
]

for tr in test_results:
    print(tr)
    