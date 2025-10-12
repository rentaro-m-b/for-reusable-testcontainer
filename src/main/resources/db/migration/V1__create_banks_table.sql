CREATE TABLE IF NOT EXISTS banks (
  id         VARCHAR(36)     NOT NULL PRIMARY KEY,
  value      INT          NOT NULL,
  unit       VARCHAR(16)  NOT NULL,
  created_at datetime    NOT NULL
);
