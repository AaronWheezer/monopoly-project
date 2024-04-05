package be.howest.ti.monopoly.web;

import be.howest.ti.monopoly.logic.IService;
import be.howest.ti.monopoly.logic.implementation.MonopolyService;
import be.howest.ti.monopoly.logic.implementation.utils.Config;
import be.howest.ti.monopoly.web.tokens.MonopolyUser;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.validation.RequestParameters;
import io.vertx.ext.web.validation.ValidationHandler;

import java.util.Objects;


/**
 * The Request class is responsible for translating information that is part of the
 * request into Java.
 * <p>
 * For every piece of information that you need from the request, you should provide a method here.
 * You can find information in:
 * - the request path: params.pathParameter("some-param-name")
 * - the query-string: params.queryParameter("some-param-name")
 * Both return a `RequestParameter`, which can contain a string or an integer in our case.
 * The actual data can be retrieved using `getInteger()` or `getString()`, respectively.
 * You can check if it is an integer (or not) using `isNumber()`.
 * <p>
 * Finally, some requests have a body. If present, the body will always be in the json format.
 * You can acces this body using: `params.body().getJsonObject()`.
 * <p>
 * **TIP:** Make sure that al your methods have a unique name. For instance, there is a request
 * that consists of more than one "player name". You cannot use the method `getPlayerName()` for both,
 * you will need a second one with a different name.
 */
public class Request {

    private final RoutingContext ctx;
    private final RequestParameters params;
    private final MonopolyUser user;


    private Request(RoutingContext ctx) {
        this.ctx = ctx;
        this.params = ctx.get(ValidationHandler.REQUEST_CONTEXT_KEY);
        this.user = (MonopolyUser) ctx.user();
    }

    public static Request from(RoutingContext ctx) {
        return new Request(ctx);
    }

    public RoutingContext getRoutingContext() {
        return ctx;
    }

    public RequestParameters getRequestParameters() {
        return params;
    }

    public boolean isAuthorized(String expectedGameId, String expectedPlayerName) {
        return Objects.equals(expectedGameId, user.getGameId()) &&
                Objects.equals(expectedPlayerName, user.getPlayerName());
    }

    public boolean isAuthorized(String expectedGameId) {
        return Objects.equals(expectedGameId, user.getGameId());
    }

    public int getTilePosition() {
        return params.pathParameter("tileId").getInteger();
    }

    public boolean hasGameId(){
        return params.pathParameter("gameId").isString();
    }

    public String getGameId() {
        return params.pathParameter("gameId").getString();
    }

    public boolean hasTilePosition() {
        return params.pathParameter("tileId").isNumber();
    }

    public String getTileName() {
        return params.pathParameter("tileId").getString();
    }

    public boolean hasStarted() {
        if(params.pathParameter("started") != null) {
            return params.pathParameter("started").isBoolean();
        }
        return false;
    }

    public boolean getStarted() {
        return params.pathParameter("started").getBoolean();
    }

    public boolean hasNumberOfPlayers() {
        if (params.pathParameter("numberOfPlayers") != null) {
            if(params.pathParameter("numberOfPlayers").isNumber()) {
                int number = params.pathParameter("numberOfPlayers").getInteger();
                if(number >= Config.minPlayers && number <= Config.maxPlayers) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getNumberOfPlayers() {
        return params.pathParameter("numberOfPlayers").getInteger();
    }

    public boolean hasPrefix() {
        if (params.pathParameter("prefix") != null) {
            return params.pathParameter("prefix").isString();
        }
        return false;
    }

    public String getPrefix() {
        return params.pathParameter("prefix").getString();
    }

    public String getBodyPlayerName() {
        return params.body().getJsonObject().getString("playerName");
    }

    public String getBodyPrefix() {
        return params.body().getJsonObject().getString("prefix");
    }

    public int getBodyNumberOfPlayers() {
        return params.body().getJsonObject().getInteger("numberOfPlayers");
    }

    public boolean hasPlayerName() {
        return params.pathParameter("playerName").isString();
    }

    public String getPlayerName() {
        return  params.pathParameter("playerName").getString();
    }

    public boolean hasPropertyName() {
        return params.pathParameter("propertyName").isString();

    }
    public String getPropertyName() {
        return  params.pathParameter("propertyName").getString();
    }

    public boolean hasDebtorName() {
        return params.pathParameter("debtorName").isString();
    }

    public String getDebtorName(){
        return params.pathParameter("debtorName").getString();
    }
}
