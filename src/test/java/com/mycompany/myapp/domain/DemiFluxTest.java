package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DemiFluxTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DemiFlux.class);
        DemiFlux demiFlux1 = new DemiFlux();
        demiFlux1.setId(1L);
        DemiFlux demiFlux2 = new DemiFlux();
        demiFlux2.setId(demiFlux1.getId());
        assertThat(demiFlux1).isEqualTo(demiFlux2);
        demiFlux2.setId(2L);
        assertThat(demiFlux1).isNotEqualTo(demiFlux2);
        demiFlux1.setId(null);
        assertThat(demiFlux1).isNotEqualTo(demiFlux2);
    }
}
