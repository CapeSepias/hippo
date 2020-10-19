package uk.nhs.digital.apispecs;

import com.github.jknack.handlebars.Options;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import uk.nhs.digital.apispecs.swagger.RequestHelper;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.MockitoAnnotations.initMocks;

public class RequestHelperTest {

    private static final String TEMPLATE_CONTENT_FROM_THE_POSITIVE_BLOCK = RandomStringUtils.random(10);
    private static final String TEMPLATE_CONTENT_FROM_THE_INVERSE_BLOCK = RandomStringUtils.random(10);

    private final RequestHelper requestHelper = RequestHelper.INSTANCE;

    @Mock private Options options;
    @Mock private Options.Buffer buffer;

    @Before
    public void setUp() throws Exception {
        initMocks(this);

        given(options.fn()).willReturn(TEMPLATE_CONTENT_FROM_THE_POSITIVE_BLOCK);
        given(options.inverse()).willReturn(TEMPLATE_CONTENT_FROM_THE_INVERSE_BLOCK);
        given(options.buffer()).willReturn(buffer);
    }

    @Test
    public void rendersBlockWhenCollectionHasAtLeastOneItem() throws IOException {

        // given
        final Collection<Boolean> paramList = Arrays.asList(false,false,true,false,false);

        // when
        final Object buffer = requestHelper.apply(paramList, options);

        // then
        assertThat("Returns Options.Buffer",
            buffer,
            instanceOf(Options.Buffer.class)
        );

        final Options.Buffer actualBuffer = (Options.Buffer) buffer;

        then(actualBuffer).should().append(TEMPLATE_CONTENT_FROM_THE_POSITIVE_BLOCK);
    }

    @Test
    public void doesNotRenderBlockWhenCollectionHasZeroItems() throws IOException {

        // given
        final Collection<Boolean> paramList = Collections.emptyList();

        // when
        final Object buffer = requestHelper.apply(paramList, options);

        // then
        assertThat("Returns Options.Buffer",
            buffer,
            instanceOf(Options.Buffer.class)
        );

        final Options.Buffer actualBuffer = (Options.Buffer) buffer;

        then(actualBuffer).should().append(TEMPLATE_CONTENT_FROM_THE_INVERSE_BLOCK);
    }

    @Test
    public void doesNotRenderBlockWhenCollectionIsNull() throws IOException {

        // given
        final Collection<Boolean> paramList = null;

        // when
        final Object buffer = requestHelper.apply(paramList, options);

        // then
        assertThat("Returns Options.Buffer",
            buffer,
            instanceOf(Options.Buffer.class)
        );

        final Options.Buffer actualBuffer = (Options.Buffer) buffer;

        then(actualBuffer).should().append(TEMPLATE_CONTENT_FROM_THE_INVERSE_BLOCK);
    }
}
