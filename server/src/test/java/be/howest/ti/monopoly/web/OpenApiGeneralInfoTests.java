package be.howest.ti.monopoly.web;

import be.howest.ti.monopoly.logic.ServiceAdapter;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;
import be.howest.ti.monopoly.logic.implementation.utils.Config;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class OpenApiGeneralInfoTests extends OpenApiTestsBase {

    @Test
    void getInfo(final VertxTestContext testContext) {
        String versionForTest = "test-version";
        service.setDelegate(new ServiceAdapter() {
            @Override
            public String getVersion() {
                return versionForTest;
            }
        });

        get(
                testContext,
                "/",
                null,
                response -> {
                    assertEquals(200, response.statusCode());
                    assertEquals("monopoly", response.bodyAsJsonObject().getString("name"));
                    assertEquals(versionForTest, response.bodyAsJsonObject().getString("version"));
                }
        );
    }

    @Test
    void getTiles(final VertxTestContext testContext) {
        service.setDelegate(new ServiceAdapter() {
            @Override
            public Tile[] getTiles() {
                return new Tile[]{};
            }
        });
        get(
                testContext,
                "/tiles",
                null,
                response -> assertOkResponse(response)
        );
    }


    @Test
    void getTileByName(final VertxTestContext testContext) {
        service.setDelegate(new ServiceAdapter() {
            @Override
            public Tile getTile(String name) {
                return Config.tiles[0];
            }
        });
        get(
                testContext,
                "/tiles/something",
                null,
                response -> assertOkResponse(response)
        );
    }

    @Test
    void getTileById(final VertxTestContext testContext) {
        service.setDelegate(new ServiceAdapter() {
            @Override
            public Tile getTile(int position) {
                return Config.tiles[0];
            }
        });
        get(
                testContext,
                "/tiles/100",
                null,
                response -> assertOkResponse(response)
        );
    }


    @Test
    void getChance(final VertxTestContext testContext) {
        service.setDelegate(new ServiceAdapter() {
            @Override
            public String[] getChance() {
                String[] chanceCards = {
                        "Advance to Boardwalk",
                        "Advance to Go (Collect $200)",
                        "Advance to Illinois Avenue. If you pass Go, collect $200",
                        "Advance to St. Charles Place. If you pass Go, collect $200",
                        "Advance to the nearest Railroad. If unowned, you may buy it from the Bank. If owned, pay owner twice the rental to which they are otherwise entitled",
                        "Advance token to nearest Utility. If unowned, you may buy it from the Bank. If owned, throw dice and pay owner a total ten times amount thrown.",
                        "Bank pays you dividend of $50",
                        "Get Out of Jail Free",
                        "Go Back 3 Spaces",
                        "Go to Jail. Go directly to Jail, do not pass Go, do not collect $200",
                        "Make general repairs on all your property. For each house pay $25. For each hotel pay $100",
                        "Speeding fine $15",
                        "Take a trip to Reading Railroad. If you pass Go, collect $200",
                        "You have been elected Chairman of the Board. Pay each player $50",
                        "Your building loan matures. Collect $150"
                };

                return chanceCards;
            }
        });
        get(
                testContext,
                "/chance",
                null,
                response -> assertOkResponse(response)
        );
    }


    @Test
    void getCommunityChest(final VertxTestContext testContext) {
        service.setDelegate(new ServiceAdapter() {
            @Override
            public String[] getCommunityChest() {
                String[] communityChestCards = {
                        "Advance to Go (Collect $200)",
                        "Advance to Illinois Ave. If you pass Go, collect $200",
                        "Advance to St. Charles Place. If you pass Go, collect $200",
                        "Advance token to nearest Utility. If unowned, you may buy it from the Bank. If owned, pay owner twice the rental to which they are otherwise entitled",
                        "Bank pays you dividend of $50",
                        "Get Out of Jail Free",
                        "Go Back 3 Spaces",
                        "Go to Jail. Go directly to Jail, do not pass Go, do not collect $200",
                        "Make general repairs on all your property. For each house pay $25. For each hotel pay $100",
                        "Pay poor tax of $15",
                        "Take a trip to Reading Railroad. If you pass Go, collect $200",
                        "Your building loan matures. Collect $150",
                        "You have won a crossword competition. Collect $100"
                };

                return communityChestCards;
            }
        });
        get(
                testContext,
                "/community-chest",
                null,
                response -> assertOkResponse(response)
        );
    }

}
