package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class FluxTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Flux.class);
        Flux flux1 = new Flux();
        flux1.setId(1L);
        Flux flux2 = new Flux();
        flux2.setId(flux1.getId());
        assertThat(flux1).isEqualTo(flux2);
        flux2.setId(2L);
        assertThat(flux1).isNotEqualTo(flux2);
        flux1.setId(null);
        assertThat(flux1).isNotEqualTo(flux2);
    }
}
