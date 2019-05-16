package Dao;

public class TestRecords {
   @Override
	public String toString() {
		return "TestRecords [num=" + num + ", userName=" + userName + ", password=" + password + ", isAdmin=" + isAdmin
				+ "]";
	}

// instance fields
   private String num;
   private String userName;
    private String password;
   private int isAdmin;
   


   public String getNum() {
      return num;
   }

   public String getUserName() {
      return userName;
   }

   public String getPassword() {
      return password;
   }

   public int getIsAdmin() {
      return isAdmin;
   }

   public boolean isAdmin() {
      return isAdmin == 1;
   }

   public TestRecords(final String num, final String userName, final String password, final String isAdmin) {
      this.num = num;
      this.userName = userName;
      this.password = password;
      this.isAdmin = isAdmin.equals("Y") ? 1: 0;
   }
}