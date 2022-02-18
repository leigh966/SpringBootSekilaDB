import re
import requests;
import json;
import testGet
import tools
import testPost
import testDelete
import testState
import sys
root = "http://localhost:8080/home/"



if len(sys.argv) > 1:
    root = "http://" + sys.argv[1] + "/home/"

print("Root: " + root)

# Add a film with minimum required params
print(testPost.add_film_min(root))

# Ensure all film titles are caps
print(testState.test_films_all_upper(root))

# Ensure all actor names are caps
print(testState.test_actors_all_upper(root))

# Delete film
print(testDelete.test_delete_film(root))

# Get an actor by id
print(testGet.test_get_actor_by_id(root))

# Query actor table by name
print(testGet.test_get_actor_upper(root)) # Upper-case
print(testGet.test_get_actor_lower(root)) # Lower-case

# Try to get get a non-existant actor by id - making sure it gives an empty list rather than an error
print(testGet.test_get_null_actor(root))

# Try to link an actor and a film that exist and are not already linked - making sure a link works in basic case
print(testPost.test_link_valid_film_actor(root)) # Request the link and ensure the backend says it has worked
print(testGet.verify_link(root, 2, 1)) # Verify that the link has actually occurred in the database

# Try to unlink the actor and film that we have just linked
print(testDelete.test_unlink_added_film_actor_link(root)) # Request the unlink and ensure the backend says it has worked
print(testGet.verify_no_link(root, 2, 1)) # Verify that the unlink has actually occurred in the database


    