package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class ContactTechniqueTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ContactTechnique.class);
        ContactTechnique contactTechnique1 = new ContactTechnique();
        contactTechnique1.setId(1L);
        ContactTechnique contactTechnique2 = new ContactTechnique();
        contactTechnique2.setId(contactTechnique1.getId());
        assertThat(contactTechnique1).isEqualTo(contactTechnique2);
        contactTechnique2.setId(2L);
        assertThat(contactTechnique1).isNotEqualTo(contactTechnique2);
        contactTechnique1.setId(null);
        assertThat(contactTechnique1).isNotEqualTo(contactTechnique2);
    }
}
