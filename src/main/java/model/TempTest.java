package model;

import dao.AppUserDao;
import dao.impl.MySQLUserDao;

public class TempTest {
    public static void main(String[] args) {
        AppUserDao dao = new MySQLUserDao();
        AppUser user1 = AppUser.UserBuilder.getBuilder()
                .name("Mark")
                .lastName("Ler")
                .email("Ler@w")
                .login("lol")
                .password("123")
                .build();
        AppUser user2 = AppUser.UserBuilder.getBuilder()
                .name("Mike")
                .lastName("Jab")
                .email("JabMike@wp.pl")
                .login("JabMike")
                .password("456123")
                .build();
        AppUser user3 = AppUser.UserBuilder.getBuilder()
                .name("Ann")
                .lastName("Jab")
                .email("JabAnn@gmail.com")
                .login("AnnJab")
                .password("qwerty")
                .build();

        dao.saveUser(user1);
        dao.saveUser(user2);
        dao.saveUser(user3);
        dao.follow(user1, user2);
        dao.follow(user3, user1);
        dao.follow(user3, user3);

        dao.unfollow(user1, user2);

        System.out.println("Followed of user1");
        dao.getFollowedUsers(user1).forEach(System.out::println);
        System.out.println("Followers of user2");
        dao.getFollowers(user2).forEach(System.out::println);

        //dao.deleteUser(user1);

    }
}
