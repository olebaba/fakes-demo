CREATE TABLE SYKMELDING
(
    ID                          VARCHAR(36) DEFAULT UUID_GENERATE_V4() PRIMARY KEY,
    PERSON                      TEXT                                  NOT NULL,
    STATUS                      TEXT                                  NOT NULL,
    FOM                         TIMESTAMP WITH TIME ZONE              NOT NULL,
    TOM                         TIMESTAMP WITH TIME ZONE              NOT NULL
);