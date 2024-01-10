# TP Spring VESLIN

Lucas Veslin 
Groupe B

---
## Commandes sur Postman
### Utilisateur

Pour ajouter un utilisateur (on peut les ajouter sans se connecter pour pouvoir facilement tester l'application mais théoriquement j'aurais dû mettre une sécurité):
http://localhost:8080/user?nom=Gaetan Pique&password=spring&role=publisher
http://localhost:8080/user?nom=Lucas&password=iutinfo&role=moderator

Pour se connecter à l'application :
http://localhost:8080/auth/login 
Sachant que il faut mettre dans le body {
    "username": "Lucas",
    "password": "iutinfo"
} par exemple
Cela va renvoyer un JWT Token à mettre dans authorisation Bearer Token de Postman.

Pour obtenir tous les utilisateurs : 
http://localhost:8080/user

Pour obtenir un utilisateur à partir de son id :
http://localhost:8080/user/2

### Article

Les résultats vont changer en fonction de l'utilisateur connecté

Pour ajouter un article : 
http://localhost:8080/article?contenu=J'adore le Java...&titre=Java Spring&nomAuteur=Gaetan Pique

Pour obtenir tous les articles : 
http://localhost:8080/article

Pour obtenir un article à partir de son nom : 
http://localhost:8080/article/Java Spring

Pour modifier un article : 
http://localhost:8080/article/Java Spring?titre=Spring&contenu=C'est long

Pour supprimer un article à partir de son nom : 
http://localhost:8080/article/Spring

### Liker
Ajouter un like à un article pour un utilisateur : 
http://localhost:8080/like?auteurId=21&articleId=18&like=true

Supprimer un like d'un utilsiateur sur un article :
http://localhost:8080/like?auteurId=21&articleId=18

Pour obtenir tous les likes : 
http://localhost:8080/like

Pour obtenir tous les likes d'un utilsiateur:
http://localhost:8080/like/user/21
