CREATE TABLE TAG(
    TAG_ID INT PRIMARY KEY NOT NULL,
    TAG_NAME NVARCHAR(50) NOT NULL,
    TAG_DESCRIPTION NVARCHAR(200)
);

CREATE TABLE CARD(
    CARD_ID INT PRIMARY KEY NOT NULL
);

CREATE TABLE NOTE(
    NOTE_ID PRIMARY KEY NOT NULL
);

CREATE TABLE CARD_TAG(
    CARD_ID PRIMARY KEY NOT NULL REFERENCES CARD(CARD_ID),
    TAG_ID PRIMARY KEY NOT NULL REFERENCES TAG(TAG_ID)
);
