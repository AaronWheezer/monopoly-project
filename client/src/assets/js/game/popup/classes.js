function getPopupContainer() {
    const $popupContainer = document.querySelector("#popupcontainer");
    return $popupContainer;
}

function removePopups(e) {
    if (e) {
        e.preventDefault();
    }
    const $popupContainer = getPopupContainer();
    $popupContainer.innerHTML = "";
    $popupContainer.classList.add("hidden");
}

function hidePopups() {
    saveToGlobal("popup", false);
    const $popupContainer = getPopupContainer();
    $popupContainer.classList.add("hidden");

    const $showButtonContainer = document.querySelector(
        "#overview > div > div + div"
    );
    removeShowPopupButton();
    const $showButton = document.createElement("button");

    $showButton.innerHTML = "Show Popup";
    $showButton.addEventListener("click", showPopups);

    $showButtonContainer.appendChild($showButton);
}

function removeShowPopupButton() {
    const $showButtonContainer = document.querySelector(
        "#overview > div > div + div"
    );
    $showButtonContainer.innerHTML = "";
}

function showPopups() {
    saveToGlobal("popup", true);
    const $popupContainer = getPopupContainer();
    removeShowPopupButton();
    $popupContainer.classList.remove("hidden");
}

class Popup {
    constructor(
        title,
        content,
        buttons = [],
        closeable = true,
        imageURL = null,
        imageAlt = null
    ) {
        this.title = title;
        this.content = content;
        this.buttons = buttons;
        this.imageURL = imageURL;
        this.imageAlt = imageAlt;
        this.closeable = closeable;
        this.init();
    }
    init() {
        this.create();
        if (!loadFromGlobal("popup")) {
            hidePopups();
        } else {
            showPopups();
        }
    }
    addButtons() {
        const $buttonContainer = document.querySelector(".popupnoflex");
        for (const button of this.buttons) {
            $buttonContainer.appendChild(button.create());
        }
        let colorButton = "gray";
        let textButton = "Hide popup";
        let event = hidePopups;
        if (this.closeable) {
            textButton = "Close";
            colorButton = "red";
            event = removePopups;
        }

        const $button = new Button(colorButton, textButton).create();
        $button.addEventListener("click", event);

        $buttonContainer.appendChild($button);

        const $popup = document.querySelector(".popup");
        const $closeButton = document.createElement("span");

        $closeButton.innerHTML = "X";
        $closeButton.classList.add("close");
        $closeButton.addEventListener("click", event);

        $popup.appendChild($closeButton);
    }
    create() {
        const $popup = document.querySelector("#popupcontainer");
        $popup.innerHTML = "";

        let imageHtml = "";
        if (this.imageURL != null) {
            imageHtml = `<img src="${this.imageURL}" alt="${this.imageAlt}">`;
        }
        $popup.innerHTML = `
                <article class="popup">
                        <h3>${this.title}</h3>
                        <p>${this.content}</p>
                        ${imageHtml}  
                        <ul class="popupnoflex">
                        </ul>
               </article>
                `;
        const $container = document.querySelector("main");

        $container.appendChild($popup);
        this.addButtons();
    }
}

class Button {
    constructor(color, text, callback) {
        this.color = color;
        this.text = text;
        this.callback = callback;
        this.create();
    }
    create() {
        const button = document.createElement("button");
        button.style.backgroundColor = this.color;
        button.innerHTML = this.text;

        button.addEventListener("click", this.callback);

        return button;
    }
}