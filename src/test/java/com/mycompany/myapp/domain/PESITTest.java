package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class PESITTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PESIT.class);
        PESIT pESIT1 = new PESIT();
        pESIT1.setId(1L);
        PESIT pESIT2 = new PESIT();
        pESIT2.setId(pESIT1.getId());
        assertThat(pESIT1).isEqualTo(pESIT2);
        pESIT2.setId(2L);
        assertThat(pESIT1).isNotEqualTo(pESIT2);
        pESIT1.setId(null);
        assertThat(pESIT1).isNotEqualTo(pESIT2);
    }
}
