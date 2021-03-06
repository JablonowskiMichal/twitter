package dao.impl;

import dao.AbstractAppUserDao;
import dao.AppUserDao;
import model.AppUser;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;

public class MySQLUserDao extends AbstractAppUserDao implements AppUserDao {


    @Override
    public HashSet<AppUser> getAll() {
        TypedQuery<AppUser> getAll = em.createQuery("from AppUser", AppUser.class);
        List<AppUser> resultList = getAll.getResultList();
        return new HashSet<>(resultList);
    }

    @Override
    public void saveUser(AppUser user) {
        hibernateUtil.save(user);

    }

    @Override
    public void deleteUser(AppUser user) {
        unfollowBeforeDelete(user);
        hibernateUtil.delete(AppUser.class, user.getId());

    }

    @Override
    public AppUser getUserById(Long id) {
        TypedQuery<AppUser> query = em.createQuery("select u from AppUser u where u.id =:id", AppUser.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public AppUser getUserByEmail(String email) {
        TypedQuery<AppUser> query = em.createQuery("select u from AppUser u where u.email =:email", AppUser.class);
        query.setParameter("email", email);
        return query.getSingleResult();
    }

    @Override
    public AppUser getUserByLogin(String login) {
        TypedQuery<AppUser> query = em.createQuery("select u from AppUser u where u.login =:login", AppUser.class);
        query.setParameter("login", login);
        return query.getSingleResult();
    }

    @Override
    public HashSet<AppUser> getFollowedUsers(AppUser loggedUser) {
        return new HashSet<>(loggedUser.getFollowing());
    }

    @Override
    public HashSet<AppUser> getNotFollowedUsers(AppUser loggedUser) {
        Query query = em.createQuery("select u from AppUser u where u not in :followed");
        query.setParameter("followed", new HashSet(loggedUser.getFollowing()));
        return new HashSet(query.getResultList());
    }

    @Override
    public HashSet<AppUser> getFollowers(AppUser loggedUser) {
        Query query = em.createQuery("select followers from AppUser u where u.id = :userId");
        query.setParameter("userId", loggedUser.getId());
        return new HashSet<>(query.getResultList());
    }

    @Override
    public void follow(AppUser loggedUser, AppUser userToFollow) {
        loggedUser.getFollowing().add(userToFollow);
        saveUser(loggedUser);

    }

    @Override
    public void unfollow(AppUser loggedUser, AppUser userToStopFollow) {
        loggedUser.getFollowing().remove(userToStopFollow);
        saveUser(loggedUser);
    }

    private void unfollowBeforeDelete(AppUser user) {
        getFollowers(user).forEach(follower -> unfollow(follower, user));
    }

}
