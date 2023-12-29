# TP Spring VESLIN

Lucas Veslin 
Groupe B

-- Je vais le terminer durant les vacances

# TP Spring VESLIN

Lucas Veslin 
Groupe B






---
##Commandes sur Postman
###Utilisateur
Pour ajouter un utilisateur :
http://localhost:8080/user/add?nom=Leblanc&password=TheWhite&role=moderator
Pour obtenir tous les utilisateurs : 
http://localhost:8080/user/all
Pour obtenir un utilisateur à partir de son id :
http://localhost:8080/user/get?id=21

###Article
Pour ajouter un article : 
http://localhost:8080/article/add?contenu=contenu&date=29/12/2012&auteurId=1
Pour obtenir tous les articles : 
http://localhost:8080/article/all
Pour obtenir un article à partir de son id : 
http://localhost:8080/article/get?id=20
Pour modifier un article (à améliorer) : 
http://localhost:8080/article/put?contenu=hi&date=12/12/2012&auteurId=1&articleId=20
Pour supprimer un article à partir de son id : 
http://localhost:8080/article/delete?id=20

###Liker
Ajouter un like à un article pour un utilisateur : 
http://localhost:8080/like/addLike?auteurId=21&articleId=18
Supprimer un like d'un utilsiateur sur un article :
http://localhost:8080/like/deleteLike?auteurId=21&articleId=18
Pour obtenir tous les likes : 
http://localhost:8080/like/allLikes
Pour obtenir tous les likes d'un utilsiateur:
http://localhost:8080/like/user?id=21

###Disliker
Ajouter un dislike à un article pour un utilisateur : 
http://localhost:8080/like/addDislike?auteurId=21&articleId=18
Supprimer un dislike d'un utilsiateur sur un article :
http://localhost:8080/like/deleteDislike?auteurId=21&articleId=18
Pour obtenir tous les dislikes : 
http://localhost:8080/like/allLikes
Pour obtenir tous les dislikes d'un utilsiateur : 
http://localhost:8080/like/userDislike?id=21
