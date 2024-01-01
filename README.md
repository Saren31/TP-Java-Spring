# TP Spring VESLIN

Lucas Veslin 
Groupe B

-- Je vais le terminer durant les vacances

---
##Commandes sur Postman
###Utilisateur
Pour ajouter un utilisateur :
http://localhost:8080/user?nom=Leblanc&password=TheWhite&role=moderator
Pour obtenir tous les utilisateurs : 
http://localhost:8080/user
Pour obtenir un utilisateur à partir de son id :
http://localhost:8080/user/21

###Article
Pour ajouter un article : 
http://localhost:8080/article?contenu=contenu&date=29/12/2012&auteurId=1
Pour obtenir tous les articles : 
http://localhost:8080/article
Pour obtenir un article à partir de son id : 
http://localhost:8080/article/20
Pour modifier un article (à améliorer) : 
http://localhost:8080/article/20?contenu=hi&date=12/12/2012&auteurId=1&titre=test
Pour supprimer un article à partir de son id : 
http://localhost:8080/article/20

###Liker
Ajouter un like à un article pour un utilisateur : 
http://localhost:8080/like?auteurId=21&articleId=18&like=true
Supprimer un like d'un utilsiateur sur un article :
http://localhost:8080/like?auteurId=21&articleId=18
Pour obtenir tous les likes : 
http://localhost:8080/like
Pour obtenir tous les likes d'un utilsiateur:
http://localhost:8080/like/user/21