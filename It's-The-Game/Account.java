class Account {
  //Declare and initalize variables
  private String username;
  private String password;
  private double money;
  private boolean hasBankrupted;

  /** Creates an Account with a username, password, money and if they were bankrupted
   * 
   * @param username is the account's username
   * @param password is the account's password
   * @money is the account's money
   * @hasBankrupted is if they user has gone bankupted before
   */ 
  public Account(String username,String password,double money, boolean hasBankrupted){
  this.username = username;
  this.password = password;
  this.money = money;
  this.hasBankrupted = hasBankrupted;
  }

  /**Checks if the input is equal to the account's username and password
   * @return if the input is equal to it's respctive username and password
   * @param username is the user's input of username
   * @param password is the user's input of password
   */
  public boolean isEqual(String username, String password){

    //If the object's username and password is equal to the argument's username and password
    if (username.equals(this.username) && password.equals(this.password)){
      return true;
    } 

    //Otherwise
    else{
      return false;
    }
  }

  /**Gets the account's username
   *@return username
   */
  public String getUsername(){
    return username;
  }

  /**Gets the account's password
   *@return password
   */  
  public String getPassword(){
    return password;
  }

  /**Gets if the user has went bankrupted
   *@return hasBankrupted
   */
  public boolean getHasBankrupted(){
    return hasBankrupted;
  }

  /**Sets if user has went bankrupted
   *@param bool is a boolean
   */
  public void setHasBankrupted(boolean bool){
    //hasBankrupted is set to bool
    this.hasBankrupted = bool;
  }

  /**Gets the account's money
   *@return money
   */
  public double getMoney(){
    return this.money;
  }

  /**Adds money to the account
   *@param money is the money added to the object's money amount
   */
  public void addMoney(double money){
    //Object's money increases its value by the agrument's money
    this.money = this.money + money; 
  }

  /** Converts the account's information to a string
   * @return the username, password, money, and if they were bankrupted
   */
  public String toString(){
    return(this.username + " " + this.password + " " + this.money + " " + this.hasBankrupted);
  }
}