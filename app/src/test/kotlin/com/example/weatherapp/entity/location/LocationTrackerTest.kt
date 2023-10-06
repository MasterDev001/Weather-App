package com.example.weatherapp.entity.location

class LocationTrackerTest {

    @Mock
    private lateinit var locationTracker: LocationTracker

    @BeforeEach
    fun setup() {
        locationTracker = mock(LocationTracker::class.java)
    }

    @Test
    fun testGetCurrentLocationWhenRequestedThenMethodIsCalled() = runBlocking {
        // Arrange
        val callback: (Double, Double) -> Unit = { _, _ -> }

        // Act
        locationTracker.getCurrentLocation(callback)

        // Assert
        verify(locationTracker).getCurrentLocation(any())
    }

    @Test
    fun testIsGpsConnectedWhenCheckedThenMethodIsCalled() {
        // Act
        locationTracker.isGpsConnected()

        // Assert
        verify(locationTracker).isGpsConnected()
    }

    @Test
    fun testEnableLocationRequestWhenRequestedThenMethodIsCalled() {
        // Arrange
        val makeRequest: (intentSenderRequest: IntentSenderRequest) -> Unit = { _ -> }

        // Act
        locationTracker.enableLocationRequest(makeRequest)

        // Assert
        verify(locationTracker).enableLocationRequest(any())
    }
}