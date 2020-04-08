package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class ContactProjetTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ContactProjet.class);
        ContactProjet contactProjet1 = new ContactProjet();
        contactProjet1.setId(1L);
        ContactProjet contactProjet2 = new ContactProjet();
        contactProjet2.setId(contactProjet1.getId());
        assertThat(contactProjet1).isEqualTo(contactProjet2);
        contactProjet2.setId(2L);
        assertThat(contactProjet1).isNotEqualTo(contactProjet2);
        contactProjet1.setId(null);
        assertThat(contactProjet1).isNotEqualTo(contactProjet2);
    }
}
