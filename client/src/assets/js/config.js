"use strict";

const _config = {
    groupnumber: '00',
    delay: 1500,
    groupPrefix:'group34',
    errorHandlerSelector: '.errormessages p',
    pawns: ['d','j','k','o','s'],
    getAPIUrl: function() { return `https://project-i.ti.howest.be/monopoly-${this.groupnumber}/api`;}
};
