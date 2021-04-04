package sdk.chat.ui.custom;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.stfalcon.chatkit.messages.MessageHolders;

import sdk.chat.core.dao.Keys;
import sdk.chat.core.dao.Message;
import sdk.chat.core.dao.User;
import sdk.chat.core.session.ChatSDK;
import sdk.chat.core.types.MessageType;
import sdk.chat.ui.R;
import sdk.chat.ui.activities.ChatActivity;
import sdk.chat.ui.chat.model.ContextRootMessageHolder;
import sdk.chat.ui.chat.model.MessageHolder;
import sdk.chat.ui.view_holders.IncomingContextRootMessageViewHolder;
import sdk.chat.ui.view_holders.OutcomingContextRootMessageViewHolder;
import sdk.chat.ui.view_holders.base.BaseIncomingContextRootMessageViewHolder;
import sdk.chat.ui.view_holders.base.BaseIncomingTextMessageViewHolder;
import sdk.chat.ui.view_holders.base.BaseOutcomingContextRootMessageViewHolder;

public class ContextRootMessageHandler extends MessageHandler {

    @Override
    public void onBindMessageHolders(Context context, MessageHolders holders) {
        MessageHolders h = holders.registerContentType(
                (byte) MessageType.IsContextRoot,
                IncomingContextRootMessageViewHolder.class,
                getContextRootPayload(context),
                R.layout.view_holder_incoming_contextroot_message,
                OutcomingContextRootMessageViewHolder.class,
                getContextRootPayload(context),
                R.layout.view_holder_outcoming_contextroot_message,
                MessageCustomizer.shared());
    }

    @Override
    public void onClick(ChatActivity activity, View rootView, Message message) {
        Toast.makeText(activity.getBaseContext(), "Context Root Message Clicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public MessageHolder onNewMessageHolder(Message message) {
        if (message.getMessageType().is(MessageType.IsContextRoot)) {
            return new ContextRootMessageHolder(message);
        }
        return null;
    }

    @Override
    public boolean hasContentFor(MessageHolder message, byte type) {
        return type == MessageType.IsContextRoot && message instanceof ContextRootMessageHolder;
    }

    private ContextRootPayload getContextRootPayload(Context context) {
        ContextRootPayload holderPayload = new ContextRootPayload();
        holderPayload.contextRootClickListener = new ContextRootClickListener() {
            @Override
            public void onReplyClick(View view, Message message) {
                String threadId = message.stringForKey(Keys.EmbeddedThreadId);
                ChatSDK.ui().startChatActivityForID(context, threadId);
            }
        };
        return holderPayload;
    }

    public static class ContextRootPayload {
        public ContextRootClickListener contextRootClickListener;
    }

    public interface ContextRootClickListener {
        void onReplyClick(View view, Message message);
    }
}
