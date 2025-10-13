import '@vaadin/polymer-legacy-adapter/style-modules.js';
import '@vaadin/login/src/vaadin-login-form.js';
import '@vaadin/vertical-layout/src/vaadin-vertical-layout.js';
import '@vaadin/common-frontend/ConnectionIndicator.js';
import '@vaadin/vaadin-lumo-styles/color-global.js';
import '@vaadin/vaadin-lumo-styles/typography-global.js';
import '@vaadin/vaadin-lumo-styles/sizing.js';
import '@vaadin/vaadin-lumo-styles/spacing.js';
import '@vaadin/vaadin-lumo-styles/style.js';
import '@vaadin/vaadin-lumo-styles/vaadin-iconset.js';

const loadOnDemand = (key) => {
  const pending = [];
  if (key === 'd6b995b72a1eaaba261c4ee7796cd9c8ef0322c6b75c323b502c3e128931db86') {
    pending.push(import('./chunks/chunk-6098117d4ff6fc1b5b007dcb9fa50a74558ca1eac8a391888e5dbc45ce402a52.js'));
  }
  if (key === 'deb4be9160cf1b20f84a906e52821a9861ae01a0bd8b94695216b29e58bc72c7') {
    pending.push(import('./chunks/chunk-d92a06a7762515ac5eda73ee8e6b3de6dd7322906dce88c06ef0f1a15023e386.js'));
  }
  if (key === '437606e79b5f6aa2e4eb66e3248e2d495efe054e2e87cce7af86f5106fdd9fab') {
    pending.push(import('./chunks/chunk-6098117d4ff6fc1b5b007dcb9fa50a74558ca1eac8a391888e5dbc45ce402a52.js'));
  }
  if (key === 'ba92b459f8d6ca86f628ba23c74191742d0c98e1764b17a2a1780937890638e4') {
    pending.push(import('./chunks/chunk-6098117d4ff6fc1b5b007dcb9fa50a74558ca1eac8a391888e5dbc45ce402a52.js'));
  }
  return Promise.all(pending);
}

window.Vaadin = window.Vaadin || {};
window.Vaadin.Flow = window.Vaadin.Flow || {};
window.Vaadin.Flow.loadOnDemand = loadOnDemand;
window.Vaadin.Flow.resetFocus = () => {
 let ae=document.activeElement;
 while(ae&&ae.shadowRoot) ae = ae.shadowRoot.activeElement;
 return !ae || ae.blur() || ae.focus() || true;
}