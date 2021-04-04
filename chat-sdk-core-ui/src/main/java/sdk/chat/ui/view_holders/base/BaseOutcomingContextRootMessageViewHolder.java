package sdk.chat.ui.view_holders.base;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.stfalcon.chatkit.messages.MessageHolders;

import java.security.Key;

import butterknife.BindView;
import butterknife.ButterKnife;
import sdk.chat.core.dao.Keys;
import sdk.chat.core.dao.Message;
import sdk.chat.ui.R;
import sdk.chat.ui.R2;
import sdk.chat.ui.chat.model.ContextRootMessageHolder;
import sdk.chat.ui.custom.ContextRootMessageHandler;
import sdk.chat.ui.module.UIModule;

public class BaseOutcomingContextRootMessageViewHolder<T extends ContextRootMessageHolder> extends MessageHolders.BaseIncomingMessageViewHolder<T> {

    @BindView(R2.id.readStatus) protected ImageView readStatus;
    @BindView(R2.id.emMsgText) protected TextView emMsgText;
    @BindView(R2.id.conversationReply) protected FloatingActionButton conversationReply;
    @BindView(R2.id.replyCount) protected TextView replyCount;

    public BaseOutcomingContextRootMessageViewHolder(View itemView, Object payload) {
        super(itemView, payload);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void onBind(T message) {
        super.onBind(message);

        Message contextRootMsg = message.getMessage();
        ContextRootMessageHandler.ContextRootPayload payload = (ContextRootMessageHandler.ContextRootPayload) this.payload;

        emMsgText.setText(contextRootMsg.stringForKey(Keys.EmbeddedMessageText));
        conversationReply.setOnClickListener((View view) -> {
            payload.contextRootClickListener.onReplyClick(view, contextRootMsg);
        });
        Integer numMsg = contextRootMsg.integerForKey(Keys.EmbeddedThreadMessageCount);
        if(numMsg == null || numMsg <= 1) {
            replyCount.setVisibility(View.GONE);
        }
        else {
            Integer numReplies = numMsg-1;
            String numRepliesText = numReplies.toString() + " replies";
            replyCount.setText(numRepliesText);
            replyCount.setOnClickListener((View view) -> {
                payload.contextRootClickListener.onReplyClick(view, contextRootMsg);
            });
        }

        UIModule.shared().getReadStatusViewBinder().onBind(readStatus, message);
        UIModule.shared().getMessageBinder().onBindSendStatus(time, message);
        UIModule.shared().getTimeBinder().bind(time, message);
    }


}
