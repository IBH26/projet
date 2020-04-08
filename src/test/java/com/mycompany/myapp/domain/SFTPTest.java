package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class SFTPTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SFTP.class);
        SFTP sFTP1 = new SFTP();
        sFTP1.setId(1L);
        SFTP sFTP2 = new SFTP();
        sFTP2.setId(sFTP1.getId());
        assertThat(sFTP1).isEqualTo(sFTP2);
        sFTP2.setId(2L);
        assertThat(sFTP1).isNotEqualTo(sFTP2);
        sFTP1.setId(null);
        assertThat(sFTP1).isNotEqualTo(sFTP2);
    }
}
