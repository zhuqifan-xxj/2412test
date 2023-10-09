package infos;

import java.util.ArrayList;
import java.util.List;

import core.User;
import ui.Column;
import ui.InfoUI;

public class ProfileInfo extends InfoUI<User> {

  @Override
  public List<Column<User>> getColumns() {
    List<Column<User>> cols = new ArrayList<>();

    // Column for Username
    cols.add(
      new Column<User>() {
        @Override
        public String getColumnName() {
          return "Your username";
        }

        @Override
        public String getValue(User dataTransferObject) {
          return dataTransferObject.getUsername();
        }
      }
    );

    // Column for Email
    cols.add(
      new Column<User>() {
        @Override
        public String getColumnName() {
          return "Email";
        }

        @Override
        public String getValue(User dataTransferObject) {
          return dataTransferObject.getEmail();
        }
      }
    );

    // Column for Full Name
    cols.add(
      new Column<User>() {
        @Override
        public String getColumnName() {
          return "Full Name";
        }

        @Override
        public String getValue(User dataTransferObject) {
          return dataTransferObject.getFullName();
        }
      }
    );

    // Column for Phone Number
    cols.add(
      new Column<User>() {
        @Override
        public String getColumnName() {
          return "Phone Number";
        }

        @Override
        public String getValue(User dataTransferObject) {
          return dataTransferObject.getPhoneNumber();
        }
      }
    );

    // Column for Custom ID
    cols.add(
      new Column<User>() {
        @Override
        public String getColumnName() {
          return "Custom ID";
        }

        @Override
        public String getValue(User dataTransferObject) {
          return dataTransferObject.getCustomId();
        }
      }
    );

    // Column for User Type
    cols.add(
      new Column<User>() {
        @Override
        public String getColumnName() {
          return "User Type";
        }

        @Override
        public String getValue(User dataTransferObject) {
          return dataTransferObject.getUserType();
        }
      }
    );

    return cols;
  }
}
