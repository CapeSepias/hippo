package uk.nhs.digital.apispecs;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.then;
import static org.mockito.MockitoAnnotations.initMocks;

import com.github.jknack.handlebars.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import uk.nhs.digital.apispecs.swagger.IsAnyTrueHelper;

import java.io.IOException;
import java.util.*;

public class IsAnyTrueHelperTest {

    private static final String TEMPLATE_CONTENT_FROM_THE_POSITIVE_BLOCK = RandomStringUtils.random(10);
    private static final String TEMPLATE_CONTENT_FROM_THE_INVERSE_BLOCK = RandomStringUtils.random(10);

    private final IsAnyTrueHelper isAnyTrueHelper = IsAnyTrueHelper.INSTANCE;

    @Mock private Options.Buffer buffer;
    private String item = "some-item";

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void rendersBlockWhenCollectionHasAtLeastOneItem() throws IOException {

        Boolean[] params = {false,false,true,false,false};
        MockOptions options = new MockOptions(params);

        // when
        final Object buffer = isAnyTrueHelper.apply(item, options);

        // then
        assertThat("Returns Options.Buffer",
            buffer,
            instanceOf(Options.Buffer.class)
        );

        final Options.Buffer actualBuffer = (Options.Buffer) buffer;

        then(actualBuffer).should().append(TEMPLATE_CONTENT_FROM_THE_POSITIVE_BLOCK);
    }

    @Test
    public void throwExceptionWhenCollectionIsEmpty() throws IOException {

        Boolean[] params = {};
        MockOptions options = new MockOptions(params);

        try {
            // when
            isAnyTrueHelper.apply(item, options);

        } catch (Exception e) {

            // then
            assertThat("Exception in execution","Exception in IsAnyTrueHelper", is(e.getMessage()));
        }
    }

    @Test
    public void throwExceptionWhenCollectionIsNull() throws IOException {

        Boolean[] params = null;
        MockOptions options = new MockOptions(params);

        try {
            // when
            isAnyTrueHelper.apply(item, options);

        } catch (Exception e) {

            // then
            assertThat("Exception in execution","Exception in IsAnyTrueHelper", is(e.getMessage()));
        }
    }

    @Test
    public void throwExceptionWhenCollectionIsNotBoolean() throws IOException {

        Object[] params = {false,"test", 4};
        MockOptions options = new MockOptions(params);

        try {
            // when
            isAnyTrueHelper.apply(item, options);

        } catch (Exception e) {

            // then
            assertThat("Exception in execution","Exception in IsAnyTrueHelper", is(e.getMessage()));
        }
    }

    class MockOptions extends Options {

        public MockOptions(Object[] params) {
            super(null, null, null, null, null, null, params, null, Collections.emptyList());
        }

        @Override
        public CharSequence fn() throws IOException {
            return TEMPLATE_CONTENT_FROM_THE_POSITIVE_BLOCK;
        }

        @Override
        public CharSequence inverse() throws IOException {
            return TEMPLATE_CONTENT_FROM_THE_INVERSE_BLOCK;
        }

        @Override
        public Buffer buffer() {
            return buffer;
        }
    }
}
