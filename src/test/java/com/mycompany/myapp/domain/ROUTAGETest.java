package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class ROUTAGETest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ROUTAGE.class);
        ROUTAGE rOUTAGE1 = new ROUTAGE();
        rOUTAGE1.setId(1L);
        ROUTAGE rOUTAGE2 = new ROUTAGE();
        rOUTAGE2.setId(rOUTAGE1.getId());
        assertThat(rOUTAGE1).isEqualTo(rOUTAGE2);
        rOUTAGE2.setId(2L);
        assertThat(rOUTAGE1).isNotEqualTo(rOUTAGE2);
        rOUTAGE1.setId(null);
        assertThat(rOUTAGE1).isNotEqualTo(rOUTAGE2);
    }
}
