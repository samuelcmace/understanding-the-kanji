package net.samuelcmace.utk.logic;

import java.sql.*;

/**
 * Defines an object wrapper for a SQLite database connection.
 */
public class DatabaseConnection {

    /**
     * The JDBC connection string that corresponds to the database.
     */
    private String dbConnectionString;

    /**
     * The connection object associated with the instance of DatabaseConnection.
     */
    public Connection activeConnection;

    /**
     * The statement associated with the instance of DatabaseConnection.
     */
    public Statement activeStatement;

    /**
     * The active result set containing the query results.
     */
    public ResultSet activeResultSet;

    /**
     * Initializes a new instance of DatabaseConnection.
     */
    public DatabaseConnection(String m_dbConnectionString) throws SQLException {
        this.dbConnectionString = m_dbConnectionString;
        Logger.ConsoleInformation("Database Connection String Set: " + this.dbConnectionString);

        this.activeConnection = DriverManager.getConnection(this.dbConnectionString);
    }

    /**
     * Destructor called by the garbage collector to close the database connection on program exit.
     * Although destructors are no longer needed in most java programming, the necessity for a DB connection to persist
     * for the entire duration of the program (but also closed upon program exit) makes it a necessity in this context.
     */
    protected void finalize() {
        try {
            this.activeConnection.close();
        } catch (SQLException e) {
            Logger.ConsoleError("There was an error in closing the database connection in the object destructor: " + e.getLocalizedMessage());
        }
    }

    /**
     * Fetch a card object from the database by Kanji character and store it in a ResultSet.
     *
     * @param m_kanji The Kanji character in question.
     */
    public void getCardByKanji(String m_kanji) throws SQLException {
        this.activeStatement = this.activeConnection.createStatement();
        this.activeResultSet = this.activeStatement.executeQuery("SELECT CARD_ID, CARD_KANJI, HEISIG_INDEX_5_EDITION, HEISIG_INDEX_6_EDITION, KEYWORD_5_EDITION, KEYWORD_6_EDITION, ON_READING, KUN_READING, NOTE FROM CARD WHERE CARD.CARD_KANJI = '" + m_kanji + "';");
    }

    /**
     * Fetch a card object from the database by the 5th Edition Heisig Index.
     *
     * @param m_heisigIndex The Heisig Index that corresponds to the  Kanji character in question.
     */
    public void getCardBy5thEditionIndex(int m_heisigIndex) throws SQLException {
        this.activeStatement = this.activeConnection.createStatement();
        this.activeResultSet = this.activeStatement.executeQuery("SELECT CARD_ID, CARD_KANJI, HEISIG_INDEX_5_EDITION, HEISIG_INDEX_6_EDITION, KEYWORD_5_EDITION, KEYWORD_6_EDITION, ON_READING, KUN_READING, NOTE FROM CARD WHERE CARD.HEISIG_INDEX_5_EDITION = '" + m_heisigIndex + "';");
    }

    /**
     * Fetch a card object from the database by the 6th Edition Heisig Index.
     *
     * @param m_heisigIndex The Heisig Index that corresponds to the  Kanji character in question.
     */
    public void getCardBy6thEditionIndex(int m_heisigIndex) throws SQLException {
        this.activeStatement = this.activeConnection.createStatement();
        this.activeResultSet = this.activeStatement.executeQuery("SELECT CARD_ID, CARD_KANJI, HEISIG_INDEX_5_EDITION, HEISIG_INDEX_6_EDITION, KEYWORD_5_EDITION, KEYWORD_6_EDITION, ON_READING, KUN_READING, NOTE FROM CARD WHERE CARD.HEISIG_INDEX_6_EDITION = '" + m_heisigIndex + "';");
    }

    /**
     * Fetch a card object from the database by the 6th Edition Heisig Keyword.
     *
     * @param m_keyword The keyword that corresponds to the Kanji character in question.
     */
    public void getCardBy5thEditionKeyword(String m_keyword) throws SQLException {
        this.activeStatement = this.activeConnection.createStatement();
        this.activeResultSet = this.activeStatement.executeQuery("SELECT CARD_ID, CARD_KANJI, HEISIG_INDEX_5_EDITION, HEISIG_INDEX_6_EDITION, KEYWORD_5_EDITION, KEYWORD_6_EDITION, ON_READING, KUN_READING, NOTE FROM CARD WHERE CARD.KEYWORD_5_EDITION = '" + m_keyword + "';");
    }

    /**
     * Fetch a card object from the database by the 6th Edition Heisig Keyword.
     *
     * @param m_keyword The keyword that corresponds to the Kanji character in question.
     */
    public void getCardBy6thEditionKeyword(String m_keyword) throws SQLException {
        this.activeStatement = this.activeConnection.createStatement();
        this.activeResultSet = this.activeStatement.executeQuery("SELECT CARD_ID, CARD_KANJI, HEISIG_INDEX_5_EDITION, HEISIG_INDEX_6_EDITION, KEYWORD_5_EDITION, KEYWORD_6_EDITION, ON_READING, KUN_READING, NOTE FROM CARD WHERE CARD.KEYWORD_6_EDITION = '" + m_keyword + "';");
    }
}
