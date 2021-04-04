package sdk.chat.ui.view_holders;

import android.view.View;

import sdk.chat.ui.chat.model.ContextRootMessageHolder;
import sdk.chat.ui.chat.model.ImageMessageHolder;
import sdk.chat.ui.view_holders.base.BaseIncomingContextRootMessageViewHolder;
import sdk.chat.ui.view_holders.base.BaseIncomingImageMessageViewHolder;

public class IncomingContextRootMessageViewHolder extends BaseIncomingContextRootMessageViewHolder<ContextRootMessageHolder> {
    public IncomingContextRootMessageViewHolder(View itemView, Object payload) {
        super(itemView, payload);
    }
}