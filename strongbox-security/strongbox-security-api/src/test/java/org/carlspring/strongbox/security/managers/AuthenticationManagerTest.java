package org.carlspring.strongbox.security.managers;

import org.carlspring.strongbox.config.CommonConfig;
import org.carlspring.strongbox.configuration.StrongboxSecurityConfig;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.assertFalse;
import static org.springframework.test.util.AssertionErrors.assertTrue;

/**
 * @author mtodorov
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = "test")
@ContextConfiguration
public class AuthenticationManagerTest
{
    @Configuration
    @Import({
            StrongboxSecurityConfig.class,
            CommonConfig.class
    })
    public static class SpringConfig { }

    @Inject
    private AuthenticationManager authenticationManager;

    @Test
    public void testLoad()
            throws Exception
    {
        assertTrue("Failed to load configuration!", authenticationManager.getConfiguration() != null);
        assertTrue("Failed to load realms!", authenticationManager.getRealms() != null);
        assertFalse("Failed to load realms!", authenticationManager.getRealms().isEmpty());
        assertTrue("Failed to load settings for anonymous access!",
                   authenticationManager.getAnonymousAccessConfiguration() != null);
        assertTrue("Failed to load settings for anonymous access!",
                   authenticationManager.getAnonymousAccessConfiguration().isEnabled());
    }

}
