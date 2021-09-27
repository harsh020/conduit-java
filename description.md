## User
-------
- TimeStampedModel : Base
- email : String
- password : String
- profile : UserProfile : [User (1 has 1) UserProfile]

-- -- 

## UserProfile
--------------
- TimeStampedModel : Base
- username : String
- bio : String
- email : String
- following : Set<UserProfile> : [UserProfile (n can follow m) User]
- followedBy : Set<UserProfile> : [UserProfile (n can be followed by m) User]

-- -- 

## Article
----------
- TimeStampedModel : Base
- slug : String
- title : String
- description : String
- body : String
- favourited : Boolean
- favouritesCount : Integer
- author : UserProfile : [Article (n has 1) UserProfile/Author]
- tag : Set<Tag> : [Article (n has m) Tags]
- comments :  Comment : [Article (1 have n) Comment]

-- --

## Tag
------
- TimeStampedModel : Base
- title : String
- description : String
- article : Set<Articles> : [Tag (n has m) Article]

-- --

## Comment
----------
- TimeStampedModel : Base
- title : String
- body : String
- like : Integer
- reply : Comment : [Comment (1 has n) Comments/Reply]
- article : Article : [Comment (n has 1) Article]