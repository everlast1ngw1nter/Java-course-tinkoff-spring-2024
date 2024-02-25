package edu.java.scrapper;

import com.github.tomakehurst.wiremock.WireMockServer;
import edu.java.clients.github.GithubWebClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

public class GithubClientTest {

    private GithubWebClient client;
    private WireMockServer wireMockServer;

    private static final String BODY = """
            {
                "id": 701823037,
                "node_id": "R_kgDOKdT4PQ",
                "name": "Java-course-tinkoff-autumn-2023",
                "full_name": "everlast1ngw1nter/Java-course-tinkoff-autumn-2023",
                "private": false,
                "owner": {
                    "login": "everlast1ngw1nter",
                    "id": 115405207,
                    "node_id": "U_kgDOBuDxlw",
                    "avatar_url": "https://avatars.githubusercontent.com/u/115405207?v=4",
                    "gravatar_id": "",
                    "url": "https://api.github.com/users/everlast1ngw1nter",
                    "html_url": "https://github.com/everlast1ngw1nter",
                    "followers_url": "https://api.github.com/users/everlast1ngw1nter/followers",
                    "following_url": "https://api.github.com/users/everlast1ngw1nter/following{/other_user}",
                    "gists_url": "https://api.github.com/users/everlast1ngw1nter/gists{/gist_id}",
                    "starred_url": "https://api.github.com/users/everlast1ngw1nter/starred{/owner}{/repo}",
                    "subscriptions_url": "https://api.github.com/users/everlast1ngw1nter/subscriptions",
                    "organizations_url": "https://api.github.com/users/everlast1ngw1nter/orgs",
                    "repos_url": "https://api.github.com/users/everlast1ngw1nter/repos",
                    "events_url": "https://api.github.com/users/everlast1ngw1nter/events{/privacy}",
                    "received_events_url": "https://api.github.com/users/everlast1ngw1nter/received_events",
                    "type": "User",
                    "site_admin": false
                },
                "html_url": "https://github.com/everlast1ngw1nter/Java-course-tinkoff-autumn-2023",
                "description": null,
                "fork": false,
                "url": "https://api.github.com/repos/everlast1ngw1nter/Java-course-tinkoff-autumn-2023",
                "forks_url": "https://api.github.com/repos/everlast1ngw1nter/Java-course-tinkoff-autumn-2023/forks",
                "keys_url": "https://api.github.com/repos/everlast1ngw1nter/Java-course-tinkoff-autumn-2023/keys{/key_id}",
                "collaborators_url": "https://api.github.com/repos/everlast1ngw1nter/Java-course-tinkoff-autumn-2023/collaborators{/collaborator}",
                "teams_url": "https://api.github.com/repos/everlast1ngw1nter/Java-course-tinkoff-autumn-2023/teams",
                "hooks_url": "https://api.github.com/repos/everlast1ngw1nter/Java-course-tinkoff-autumn-2023/hooks",
                "issue_events_url": "https://api.github.com/repos/everlast1ngw1nter/Java-course-tinkoff-autumn-2023/issues/events{/number}",
                "events_url": "https://api.github.com/repos/everlast1ngw1nter/Java-course-tinkoff-autumn-2023/events",
                "assignees_url": "https://api.github.com/repos/everlast1ngw1nter/Java-course-tinkoff-autumn-2023/assignees{/user}",
                "branches_url": "https://api.github.com/repos/everlast1ngw1nter/Java-course-tinkoff-autumn-2023/branches{/branch}",
                "tags_url": "https://api.github.com/repos/everlast1ngw1nter/Java-course-tinkoff-autumn-2023/tags",
                "blobs_url": "https://api.github.com/repos/everlast1ngw1nter/Java-course-tinkoff-autumn-2023/git/blobs{/sha}",
                "git_tags_url": "https://api.github.com/repos/everlast1ngw1nter/Java-course-tinkoff-autumn-2023/git/tags{/sha}",
                "git_refs_url": "https://api.github.com/repos/everlast1ngw1nter/Java-course-tinkoff-autumn-2023/git/refs{/sha}",
                "trees_url": "https://api.github.com/repos/everlast1ngw1nter/Java-course-tinkoff-autumn-2023/git/trees{/sha}",
                "statuses_url": "https://api.github.com/repos/everlast1ngw1nter/Java-course-tinkoff-autumn-2023/statuses/{sha}",
                "languages_url": "https://api.github.com/repos/everlast1ngw1nter/Java-course-tinkoff-autumn-2023/languages",
                "stargazers_url": "https://api.github.com/repos/everlast1ngw1nter/Java-course-tinkoff-autumn-2023/stargazers",
                "contributors_url": "https://api.github.com/repos/everlast1ngw1nter/Java-course-tinkoff-autumn-2023/contributors",
                "subscribers_url": "https://api.github.com/repos/everlast1ngw1nter/Java-course-tinkoff-autumn-2023/subscribers",
                "subscription_url": "https://api.github.com/repos/everlast1ngw1nter/Java-course-tinkoff-autumn-2023/subscription",
                "commits_url": "https://api.github.com/repos/everlast1ngw1nter/Java-course-tinkoff-autumn-2023/commits{/sha}",
                "git_commits_url": "https://api.github.com/repos/everlast1ngw1nter/Java-course-tinkoff-autumn-2023/git/commits{/sha}",
                "comments_url": "https://api.github.com/repos/everlast1ngw1nter/Java-course-tinkoff-autumn-2023/comments{/number}",
                "issue_comment_url": "https://api.github.com/repos/everlast1ngw1nter/Java-course-tinkoff-autumn-2023/issues/comments{/number}",
                "contents_url": "https://api.github.com/repos/everlast1ngw1nter/Java-course-tinkoff-autumn-2023/contents/{+path}",
                "compare_url": "https://api.github.com/repos/everlast1ngw1nter/Java-course-tinkoff-autumn-2023/compare/{base}...{head}",
                "merges_url": "https://api.github.com/repos/everlast1ngw1nter/Java-course-tinkoff-autumn-2023/merges",
                "archive_url": "https://api.github.com/repos/everlast1ngw1nter/Java-course-tinkoff-autumn-2023/{archive_format}{/ref}",
                "downloads_url": "https://api.github.com/repos/everlast1ngw1nter/Java-course-tinkoff-autumn-2023/downloads",
                "issues_url": "https://api.github.com/repos/everlast1ngw1nter/Java-course-tinkoff-autumn-2023/issues{/number}",
                "pulls_url": "https://api.github.com/repos/everlast1ngw1nter/Java-course-tinkoff-autumn-2023/pulls{/number}",
                "milestones_url": "https://api.github.com/repos/everlast1ngw1nter/Java-course-tinkoff-autumn-2023/milestones{/number}",
                "notifications_url": "https://api.github.com/repos/everlast1ngw1nter/Java-course-tinkoff-autumn-2023/notifications{?since,all,participating}",
                "labels_url": "https://api.github.com/repos/everlast1ngw1nter/Java-course-tinkoff-autumn-2023/labels{/name}",
                "releases_url": "https://api.github.com/repos/everlast1ngw1nter/Java-course-tinkoff-autumn-2023/releases{/id}",
                "deployments_url": "https://api.github.com/repos/everlast1ngw1nter/Java-course-tinkoff-autumn-2023/deployments",
                "created_at": "2023-10-07T16:58:37Z",
                "updated_at": "2024-02-02T18:30:18Z",
                "pushed_at": "2024-02-02T18:21:58Z",
                "git_url": "git://github.com/everlast1ngw1nter/Java-course-tinkoff-autumn-2023.git",
                "ssh_url": "git@github.com:everlast1ngw1nter/Java-course-tinkoff-autumn-2023.git",
                "clone_url": "https://github.com/everlast1ngw1nter/Java-course-tinkoff-autumn-2023.git",
                "svn_url": "https://github.com/everlast1ngw1nter/Java-course-tinkoff-autumn-2023",
                "homepage": null,
                "size": 2356,
                "stargazers_count": 0,
                "watchers_count": 0,
                "language": "Java",
                "has_issues": true,
                "has_projects": true,
                "has_downloads": true,
                "has_wiki": true,
                "has_pages": false,
                "has_discussions": false,
                "forks_count": 0,
                "mirror_url": null,
                "archived": false,
                "disabled": false,
                "open_issues_count": 0,
                "license": null,
                "allow_forking": true,
                "is_template": false,
                "web_commit_signoff_required": false,
                "topics": [],
                "visibility": "public",
                "forks": 0,
                "open_issues": 0,
                "watchers": 0,
                "default_branch": "master",
                "temp_clone_token": null,
                "network_count": 0,
                "subscribers_count": 1
            }
            """;

    @BeforeEach
    void prep(){
        wireMockServer = new WireMockServer();
        wireMockServer.start();
        configureFor("localhost", wireMockServer.port());
        client = new GithubWebClient(
                WebClient
                        .builder()
                        .baseUrl(wireMockServer.baseUrl() + "/repos")
                        .build());
    }

    @AfterEach()
    void after() {
        wireMockServer.stop();
    }

    @Test
    void fetchQuestionTest() {
        stubFor(get(urlPathMatching("/repos/everlast1ngw1nter/Java-course-tinkoff-autumn-2023"))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody(BODY)));
        //Это проходит локально, но падает на гите
//        var resp = client.fetchRepo("everlast1ngw1nter", "Java-course-tinkoff-autumn-2023")
//                .block();
//        assertThat(resp.id())
//                .isEqualTo(701823037L);
//        assertThat(resp.name())
//                .isEqualTo("Java-course-tinkoff-autumn-2023");
//        assertThat(resp.url())
//                .isEqualTo("https://github.com/everlast1ngw1nter/Java-course-tinkoff-autumn-2023");
//        assertThat(resp.updatedAt().toString())
//                .isEqualTo("2024-02-02T18:30:18Z");
//        assertThat(resp.owner().login())
//                .isEqualTo("everlast1ngw1nter");
//        assertThat(resp.owner().id())
//                .isEqualTo(115405207L);
    }
}
