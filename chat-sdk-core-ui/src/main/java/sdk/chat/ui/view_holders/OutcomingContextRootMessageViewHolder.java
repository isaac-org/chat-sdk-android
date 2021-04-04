package sdk.chat.ui.view_holders;

import android.view.View;

import sdk.chat.ui.chat.model.ContextRootMessageHolder;
import sdk.chat.ui.view_holders.base.BaseIncomingContextRootMessageViewHolder;
import sdk.chat.ui.view_holders.base.BaseOutcomingContextRootMessageViewHolder;

public class OutcomingContextRootMessageViewHolder extends BaseOutcomingContextRootMessageViewHolder<ContextRootMessageHolder> {
    public OutcomingContextRootMessageViewHolder(View itemView, Object payload) {
        super(itemView, payload);
    }
}