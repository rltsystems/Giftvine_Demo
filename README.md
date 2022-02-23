# Group5_CST438_Project02

## Team Members

1. Ignacio Gramajo
2. Ryan Trinh
3. Lucas Kotowski
4. Andrew Liddle

## App Description
This is a web based wishlist application using modern web frameworks. This will require the development of a Representational state transfer (REST) Application Programming Interface (API), a constant integration, constant development (CICD) pipeline, and a persistence (Database) layer. The website will be deployed to a Platform as a Service (PaaS) such as Heroku or Google App Engine.

## Site Map (Minimum Pages)
# Landing page
-The welcome page where users can login or create an account
#  List page
- A page listing all the things the user wants
- Links, Descriptions, and pictures
- Note: DO NOT store the picture in the database (that is why we have CDNs) store
a URL in the DB.
-  Host the image on imgur (or something similar), or just hotlink it… (we would
NOT hot link a resource in a real environment. That is how you get sued)
- Unsplash is great source of royalty free images
# Edit item page
- Allow the user to change the URL, image, description and priority of the item.
# Create account page
- We have all seen these. Ask for a username and password
- Do not allow duplicate usernames
- enforce simple password rules (minimum length >=6 characters, alphanumeric
with at least one special character)
# Login page
- Standard username and password page
# Edit user profile page
- Allow the user to change their name and password
- Allow the user to delete their account. Deleting the account should get rid of all
their database items.

