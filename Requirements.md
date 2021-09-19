## Actors
---------

- User
- Guest<sup>*</sup> 
- System

-- --

## Use Case
-----------

- User
    - view/update profile
    - view/follow other users
    - view/create/update/favourite/delete articles
    - view/create/update/delete comments

- Guest
    - view other users
    - view articles
    - view comments 

-- --

## Entities
-----------

- User
- UserProfile
- Article
- Tag
- Comment

-- -- 

## Relationships
----------------

- User (1 has 1) UserProfile
- User (1 has n) Article
- User (m follows m) User
- User (n favourites m) Article
- User (1 has n) Comment
- Article (n has m) Tag
- Article (1 has n) Comment

-- --