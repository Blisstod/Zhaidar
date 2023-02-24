package entities;

public class User {
    private int id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private double balance;
    public User(){}
    public User(String login, String password, String name, String surname, double balance) {
        setLogin(login);
        setPassword(password);
        setName(name);
        setSurname(surname);
        setBalance(balance);
    }

    public User(double balance){
        setBalance(balance);
    }
    public User(String login, String password){
        //setId(id);
        setLogin(login);
        setPassword(password);
    }

    public User(int id,String login,String password,  String name, String surname, double balance) {
        setId(id);
        setLogin(login);
        setPassword(password);
        setName(name);
        setSurname(surname);
        setBalance(balance);
    }
    public void setId(int id){
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }


    public void setBalance(double balance) {
        this.balance = balance;
    }
    public void pay (double price) {
        this.balance = balance-price;
    }
    public void refund (double price) {
        this.balance = balance+price;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "User{" +
                ", id='" + id + '\'' +
                ", login='" + login + '\'' +
                ", password=" + password +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", balance=" + balance +
                '}';
    }

//public static class UserBuilder{
//        private String login;
//        private String password;
//        private String name;
//        private String surname;
//        private double balance;
//
//        public UserBuilder (String login, String password, String name, String surname, double balance){
//            this.login = login;
//            this.password = password;
//            this.name=name;
//            this.surname=surname;
//            this.balance=balance;
//        }
//
//        public User build(){
//            return new User(this);
//        }
//    }
}