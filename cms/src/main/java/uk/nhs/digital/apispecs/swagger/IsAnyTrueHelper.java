package uk.nhs.digital.apispecs.swagger;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

public class IsAnyTrueHelper implements Helper<String> {

    public static final IsAnyTrueHelper INSTANCE = new IsAnyTrueHelper();

    public static final String NAME = "isAnyTrue";

    @Override public Object apply(final String items, final Options options) throws IOException {

        if (!isValid(options)) {

            throw new RuntimeException("Exception in IsAnyTrueHelper");
        }

        final Options.Buffer buffer = options.buffer();

        if (hasRequestParams(options)) {
            buffer.append(options.fn());
        } else {
            buffer.append(options.inverse());
        }

        return buffer;
    }

    private boolean hasRequestParams(final Options options) {
        return Arrays.stream(Optional.ofNullable(options).get().params)
            .map(param -> (Boolean)param)
            .anyMatch(param -> param);
    }

    private Boolean isValid(Options options) {

        if (options == null || options.params == null || (!(options.params.length > 0))) {
            return false;
        }

       return Arrays.stream(options.params).allMatch(obj -> obj instanceof Boolean);
    }
}
