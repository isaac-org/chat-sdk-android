package sdk.chat.ui.chat.model;

import com.stfalcon.chatkit.commons.models.MessageContentType;

import sdk.chat.core.dao.Message;

public class ContextRootMessageHolder extends MessageHolder implements MessageContentType {

    public ContextRootMessageHolder(Message message) {
        super(message);
    }

}
