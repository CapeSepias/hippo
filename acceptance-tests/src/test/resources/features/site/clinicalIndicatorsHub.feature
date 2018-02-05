Feature: Clinical Indicator hub page and sub sections

    As a site user I should see a summary page of all the Clinical Indicator
    sections so I can navigate to each one individually to find out more.

    Scenario: Clinical Indicators hub page
        Given I navigate to the "home" page
        Then I should see a page titled "Clinical Indicators"

    Scenario: CI landing pages - CCG Outcomes (and back link)
        Given I navigate to the "home" page
        When I click on link "Clinical Commissioning Group Outcomes Indicator Set"
        Then I should see page titled "CCG Outcomes - Indicator Set"
        When I click on link "Back to Clinical Indicators"
        Then I should see a page titled "Clinical Indicators"

    Scenario: CI landing pages - CCG Outcomes (and back link)
        Given I navigate to the "home" page
        When I click on link "Clinical Commissioning Group Outcomes Indicator Set"
        Then I should see page titled "CCG Outcomes - Indicator Set"
        When I click on link "Browse CCG Outcomes"
        Then I should see the "CATEGORY" list including:
            | Clinical Commissioning Groups Outcomes Framework   |
        And I should see 1 search result

    Scenario: CI landing pages - Compendium
        Given I navigate to the "home" page
        When I click on link "Compendium of Population Health Indicators"
        Then I should see page titled "Compendium of Population Health Indicators"
        When I click on link "Browse Compendium Indicators"
        Then I should see the "CATEGORY" list including:
            | Compendium   |
        And I should see 1 search result

    Scenario: CI landing pages - Social Care
        Given I navigate to the "home" page
        When I click on link "Social Care"
        Then I should see page titled "Social Care"
        When I click on link "Browse Social Care"
        Then I should see the "CATEGORY" list including:
            | Adult Social Outcomes Framework (ASCOF)   |
        And I should see 1 search result

    Scenario: CI landing pages - NHS Outcomes Framework
        Given I navigate to the "home" page
        When I click on link "NHS Outcomes Framework"
        Then I should see page titled "NHS Outcomes Framework"
        When I click on link "Browse NHS Outcomes"
        Then I should see the "CATEGORY" list including:
            | NHS Outcomes Framework   |
        And I should see 1 search result

    Scenario: CI landing pages - SHMI
        Given I navigate to the "home" page
        When I click on link "Summary Hospital-level Mortality Indicator (SHMI)"
        Then I should see page titled "Summary Hospital-level Mortality Indicator (SHMI)"
        When I click on link "Browse SHMI"
        Then I should see the "CATEGORY" list including:
            | Summary Hospital-level Mortality Indicator (SHMI)   |
        And I should see 1 search result

    Scenario: CI landing pages - POMI
        Given I navigate to the "home" page
        When I click on link "Patient Online Management Information (POMI)"
        Then I should see page titled "Patient Online Management Information (POMI)"