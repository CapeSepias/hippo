package uk.nhs.digital.apispecs.swagger;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

public class RequestHelper implements Helper<Collection<Boolean>> {

    public static final RequestHelper INSTANCE = new RequestHelper();

    public static final String NAME = "hasRequestParams";

        @Override public Object apply(final Collection<Boolean> items, final Options options) throws IOException {
            final Options.Buffer buffer = options.buffer();

            if (hasRequestParams(items)) {
                buffer.append(options.fn());
            } else {
                buffer.append(options.inverse());
            }

            return buffer;
        }

        private boolean hasRequestParams(final Collection<Boolean> items) {
            return Optional.ofNullable(items).get().stream().anyMatch( object -> object == true);
        }
}
