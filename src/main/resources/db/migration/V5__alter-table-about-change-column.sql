ALTER TABLE tb_about
CHANGE bio paragraphOne TEXT NOT NULL,
ADD COLUMN paragraphTwo TEXT,
ADD COLUMN paragraphThree TEXT;